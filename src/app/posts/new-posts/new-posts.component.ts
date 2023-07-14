import { Component } from '@angular/core';

@Component({
  selector: 'app-new-posts',
  templateUrl: './new-posts.component.html',
  styleUrls: ['./new-posts.component.scss']
})
export class NewPostsComponent {

  permalink: string = '';
  imgSrc: any = './assets/placeholder.png';

  onTitleChanged($event: any){
    const title = $event.target.value;
    this.permalink = title.replace(/\s/g,'-');
  }

  showPreview($event:any){
    const reader = new FileReader();
    reader.onload = (e) =>{
     this.imgSrc = e.target?.result
    }

    reader.readAsDataURL($event?.target.files[0])
  }
}
