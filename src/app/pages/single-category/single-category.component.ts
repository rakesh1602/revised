import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-single-category',
  templateUrl: './single-category.component.html',
  styleUrls: ['./single-category.component.scss']
})
export class SingleCategoryComponent implements OnInit {
  postArray!: Array<Object>
  category!: any;

  constructor(private route : ActivatedRoute, private postService: PostService){}

  ngOnInit(): void {
    this.route.params.subscribe(val =>{
      this.category = val;
      console.log(val)
      this.postService.loadCategoryPost(val?.['id']).subscribe(val=>{
        this.postArray = val.map((item) => {
          const data = item.data as {
            title: string;
            permalink: string;
            postImgPath: string;
            category: { category: string; categoryId: string };
            content: string;
            excerpt: string;
            createdAt: { toDate(): Date };
            isFeatured: boolean;
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
            createdAt: data.createdAt.toDate(),
            isFeatured: data.isFeatured,
          };
        });
      });
      })
  
  }
}
