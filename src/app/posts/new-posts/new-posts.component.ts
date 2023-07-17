import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Post } from 'src/app/models/post';
import { CategoriesService } from 'src/app/services/categories.service';
import { PostService } from 'src/app/services/post.service';

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
  postForm: FormGroup;

  constructor(private categoryService: CategoriesService, private formBuilder: FormBuilder, private postService: PostService) {
    this.postForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(15)]],
      permalink: [{ value: '' }, Validators.required],
      excerpt: ['', Validators.required],
      category: ['', Validators.required],
      postImg: ['', Validators.required],
      content: ['', Validators.required]
    })
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

    this.postService.uploadImage(this.selectedImg, postData)

    this.postService.saveData(postData)
    console.log(postData)
  }
}
