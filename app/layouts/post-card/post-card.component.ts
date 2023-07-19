import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/models/post';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent implements OnInit {

  @Input() postData!:any  
  
  get title(): string {
    return this.postData ? this.postData.title : '';
  }

  constructor(){}

  ngOnInit(): void {
   console.log(this.postData);
  }

}
