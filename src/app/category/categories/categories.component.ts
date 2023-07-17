import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})


export class CategoriesComponent implements OnInit {


  category!: string;
  categoryArray: Array<{ data: { category: string }, id: { id: string } }> = [];
  formStatus = 'Add';
  categoryId!: string

  constructor(private categoryService: CategoriesService) { }

  ngOnInit(): void {
    this.categoryService.getData().subscribe(val => {
      console.log(val);
      this.categoryArray = val.map(item => ({ data: item.data as { category: string}, id: item.id as unknown as {id:string}}));
    });
  }


  addCategory(category: string) {

    let categoryData: Category = {
      category: category,
    }

    if(this.formStatus == 'Add'){
    this.categoryService.saveData(categoryData);
    this.category = '';
    } else if(this.formStatus == 'Edit'){
      this.categoryService.updateData(this.categoryId,categoryData)
      this.category = ''
      this.formStatus = 'Add'
    }

    // this.afs.collection('categories').add(categoryData).then(docRef => {
    //   console.log(docRef);

    // this.afs.collection('categories').doc(docRef.id).collection('subcategories').add(subcategory).then(docref1=>{
    //   console.log(docref1)
    // })
    // })
    //   .catch(err => console.log(err))
    // console.log(categoryData)
  }

  updateCategory(category: any, id: any) {
    console.log(category)
    this.category = category;
    this.formStatus = "Edit"
    this.categoryId = id
  }
  
  deleteCategory(id:any){
    this.categoryService.deleteData(id);
  }
}
