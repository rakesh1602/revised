import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-all-post',
  templateUrl: './all-post.component.html',
  styleUrls: ['./all-post.component.scss']
})
export class AllPostComponent implements OnInit{

  postArray: Array<{
    title: string;
    id: string;
    permalink: string;
    postImgPath: string;
    category: {category: string, categoryId: string };
    content: string;
    excerpt: string;
    createdAt: Date;
  }> = [];
  
  constructor(private postService: PostService){}

  ngOnInit(): void {
    this.postService.getData().subscribe(val => {
      console.log(val);
      this.postArray = val.map(item => {
        const data = item.data as {
          title: string;
          permalink: string;
          postImgPath: string;
          category: { category: string, categoryId:string };
          content: string;
          excerpt: string;
          createdAt: { toDate(): Date }; 
        };
  
        return {
          title: data.title,
          id: item.id,
          permalink: data.permalink,
          postImgPath: data.postImgPath,
          category: data.category,
          categoryId: data.category.categoryId,
          content: data.content,
          excerpt: data.excerpt,
          createdAt: data.createdAt.toDate() 
        };
      });
    });
  }

  onDelete(postImgPath:any, id:any){
    this.postService.deleteImage(postImgPath, id);
  }
}
