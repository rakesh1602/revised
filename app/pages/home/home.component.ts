import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{

  featuredPostArray! : Array<Object>

  constructor(private postService: PostService){}

  ngOnInit(): void {
    this.postService.loadFeatured().subscribe((val) => {
      console.log(val);
      this.featuredPostArray = val.map((item) => {
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
  }

}
