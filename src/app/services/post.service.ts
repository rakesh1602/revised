import { Injectable } from '@angular/core';
import { AngularFirestore, DocumentSnapshot } from '@angular/fire/compat/firestore';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { map } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private storage: AngularFireStorage, private fireStore: AngularFirestore, private toaster: ToastrService, private router:Router) { }

  uploadImage(selectedImg: any, postData: any, formStatus: any, id: any) {
    const filePath = `postIMG/${Date.now()}`;
    console.log(filePath);

    this.storage.upload(filePath, selectedImg).then(() => {
      console.log('Post images uplaod successfully.')

      this.storage.ref(filePath).getDownloadURL().subscribe(URL => {
        postData.postImgPath = URL
        console.log(URL)

        if(formStatus == 'Edit'){
          this.updateData(id,postData)
        }else{
        this.saveData(postData)
        }
      })
    })
  }


  saveData(postData:any){
    this.fireStore.collection('posts').add(postData).then(docref=>{
      console.log(docref)
      this.toaster.success("Posts added successfully.")
      this.router.navigate(['/posts'])
    })
  }


  getData(){
    return this.fireStore.collection('posts').snapshotChanges().pipe(
      map(actions =>{
        return actions.map(a=>{
          const data = a.payload.doc.data();
          const id = a.payload.doc.id;

          return {id, data};
        })
      })
    )
  }

 
  loadEditData(id: any) {
    return this.fireStore.doc(`/posts/${id}`).valueChanges();
  }

  updateData(id: any, postData: any){
    this.fireStore.doc(`posts/${id}`).update(postData).then(()=>{
      this.toaster.success("Data updated successfully");
      this.router.navigate(['/posts']);
    })
  }

  deleteImage(postImgPath: any, id: any){
    this.storage.storage.refFromURL(postImgPath).delete().then(()=>{
      this.deleteData(id)
      console.log("Image delete successfully.")
    })
  }

  deleteData(id: any){
    this.fireStore.doc(`posts/${id}`).delete().then(()=>{
      this.toaster.warning("Post deleted successfully.")
    })
  }

  markAsFeatured(id: any, featuredData: any){
    this.fireStore.doc(`posts/${id}`).update(featuredData).then(()=>{
      this.toaster.info("Featured updated.")
    })
  }
  
}
