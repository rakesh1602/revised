import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { ToastrService } from 'ngx-toastr';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private storage: AngularFireStorage, private fireStore: AngularFirestore, private toaster: ToastrService) { }

  uploadImage(selectedImg: any, postData: any) {
    const filePath = `postIMG/${Date.now()}`;
    console.log(filePath);

    this.storage.upload(filePath, selectedImg).then(() => {
      console.log('Post images uplaod successfully.')

      this.storage.ref(filePath).getDownloadURL().subscribe(URL => {
        postData.postImgPath = URL
        console.log(URL)

        this.saveData(postData)

      })
    })
  }


  saveData(postData:any){
    this.fireStore.collection('posts').add(postData).then(docref=>{
      console.log(docref)
      this.toaster.success("Posts added successfully.")
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
}
