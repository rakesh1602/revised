import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { PostService } from "src/app/services/post.service";

@Component({
  selector: "app-single-post",
  templateUrl: "./single-post.component.html",
  styleUrls: ["./single-post.component.scss"],
})
export class SinglePostComponent implements OnInit {
  postData: any
  category!: any;
  similarPost! : Array<Object>

  constructor(private route : ActivatedRoute, private postService: PostService){}

ngOnInit(): void {
    this.route.params.subscribe(val =>{

      this.postService.countViews(val?.['id']);

      this.postService.loadOnePost(val?.['id']).subscribe(post =>{
        this.postData = post;
        this.loadSimilarPost(this.postData.category.categoryId)
      })
    })
}


loadSimilarPost(categoryId: any){
  this.postService.loadSimilar(categoryId).subscribe(val =>{
    this.similarPost = val.map((item) => {
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