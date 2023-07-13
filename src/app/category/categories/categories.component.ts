import { Component, OnInit } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore/';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})


export class CategoriesComponent implements OnInit {

  isDisabled = true;

  constructor(private afs: AngularFirestore) { }
  ngOnInit(): void {
  }



  category: string | undefined;

  addCategory(category: any) {

    let categoryData = {
      category: category,
      status: 'active'
    }

    let subcategory = {
      category: category
    }

    this.afs.collection('categories').add(categoryData).then(docRef => {
      console.log(docRef);

    this.afs.collection('categories').doc(docRef.id).collection('subcategories').add(subcategory).then(docref1=>{
      console.log(docref1)
    })
    })
      .catch(err => console.log(err))
    console.log(categoryData)
  }

}
