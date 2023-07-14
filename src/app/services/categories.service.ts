import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore/';
import { ToastrService } from 'ngx-toastr';
import { map } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private afs: AngularFirestore, private toaster: ToastrService) { }


  saveData(data:any){
    this.afs.collection('categories').add(data).then(docRef =>{
      console.log(docRef)
      this.toaster.success("Category added successfully.")
    }).catch(err =>{
      console.log(err)
      this.toaster.error("Failed to save category" + err)
    })
  }

  getData(){
    return this.afs.collection('categories').snapshotChanges().pipe(
      map(actions =>{
        return actions.map(a=>{
          const data = a.payload.doc.data();
          const id = a.payload.doc.id;

          return {id, data};
        })
      })
    )
  }
  updateData(id:string, editData:any){
    this.afs.collection('categories').doc(id).update(editData).then(docRef =>{
      console.log(docRef)
      this.toaster.success("Category updated successfully.")
    }).catch(err=>{
      console.log(err)
      this.toaster.error("Failed to update category" + err)
    })

  }

  deleteData(id: string){
    this.afs.collection('categories').doc(id).delete().then(docRef =>{
      console.log(docRef)
      this.toaster.warning("Categories removed successfully.")
    })
  }
}
