import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/models/post';
import { CategoriesService } from 'src/app/services/categories.service';
import { PostService } from 'src/app/services/post.service';
import { collection, doc, getDoc, getFirestore, setDoc } from "firebase/firestore"; 

@Component({
  selector: 'app-new-posts',
  templateUrl: './new-posts.component.html',
  styleUrls: ['./new-posts.component.scss']
})
export class NewPostsComponent implements OnInit {

  permalink: string = '';
  imgSrc: any = './assets/placeholder.png';
  selectedImg: any;
  categoryArray: Array<{ data: { category: string }, id: { id: string } }> = [];
  postForm!: FormGroup;
  postDetails: any;
  posts: any;
  formStatus : string = "Add New"
  docId!: string;
 

  constructor(private categoryService: CategoriesService, private formBuilder: FormBuilder, private postService: PostService, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(value => {
      console.log(value);
      const postId = value['id'];
      
      this.docId= value['id']

      if(this.docId){
        this.postService.loadEditData(postId).subscribe(posts => {
          console.log("results", posts);
          this.posts = posts;
    
          // Extract the category details from the posts object
          const category = this.posts.category;
          const categoryId = category.categoryId;
          const categoryName = category.category;
    
          // Build the form
          this.postForm = this.formBuilder.group({
            title: [this.posts.title, [Validators.required, Validators.minLength(15)]],
            permalink: [this.posts.permalink, Validators.required],
            excerpt: [this.posts.excerpt, Validators.required],
            category: [categoryName, Validators.required],
            postImg: ['', Validators.required],
            content: [this.posts.content, Validators.required]
          });
    
          this.imgSrc = this.posts.postImgPath;
          this.formStatus = "Edit"
        });
      } else{
        this.postForm = this.formBuilder.group({
          title: ['', [Validators.required, Validators.minLength(15)]],
          permalink: ['', Validators.required],
          excerpt: ['', Validators.required],
          category: ['', Validators.required],
          postImg: ['', Validators.required],
          content: ['', Validators.required]
        });

      }
      // Fetch post data
    
    });
  }
  
  
  


  ngOnInit(): void {
    this.categoryService.getData().subscribe(val => {
      console.log(val);
      this.categoryArray = val.map(item => ({ data: item.data as { category: string }, id: item.id as unknown as { id: string } }));
    });
  }

  get formControl() {
    return this.postForm.controls;
  }

  onTitleChanged($event: any) {
    const title = $event.target.value;
    this.permalink = title.replace(/\s/g, '-');
  }

  showPreview($event: any) {
    const reader = new FileReader();
    reader.onload = (e) => {
      this.imgSrc = e.target?.result
    }

    reader.readAsDataURL($event?.target.files[0])
    this.selectedImg = $event.target.files[0];
  }

  onSubmit() {
    console.log(this.postForm.value)

    let splitted = this.postForm.value.category.split(',');
    console.log(splitted)

    const postData: Post = {
      title: this.postForm.value.title,
      permalink: this.postForm.value.permalink || '',  //Permalink 
      category: {
        categoryId: splitted[0],
        category: splitted[1]
      },
      postImgPath: '',
      excerpt: this.postForm.value.excerpt,
      content: this.postForm.value.content,
      isFeatured: false,
      views: 0,
      status: 'new',
      createdAt: new Date()
    }

    this.postService.uploadImage(this.selectedImg, postData, this.formStatus, this.docId)

    this.postService.saveData(postData)
    console.log(postData)
    this.postForm.reset();
    this.imgSrc = './assets/placeholder.png';
  }
}
