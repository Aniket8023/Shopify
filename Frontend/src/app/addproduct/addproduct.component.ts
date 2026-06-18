import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {

  constructor(private service:HttpService,
    private router:Router
){}

ngOnInit(): void {

}

product = {
  productTitle: '',
  originalPrice: 0,
  description: '',
  productCategory: '',
  discountPercentage: 0,
  sellingPrice: 0,
  productQuantity: 0,
  ratings: 0,
};


previewImage: string | ArrayBuffer | null|undefined = null;
productImage!:File;


onSubmit(form: any, inputFile: any) {
  if (form.valid) {

    // ✅ Calculate selling price
    this.product.sellingPrice = this.product.originalPrice - 
      (this.product.originalPrice * this.product.discountPercentage / 100);

    console.log('Product Added:', this.product);

    this.service.PostProductData(this.product, this.productImage)
      .subscribe((response) => {
        console.log(response);
        this.router.navigate(['/adminDashboard']);
      });

    form.reset();
    this.previewImage = null;
    inputFile.value = '';
  }
}




onImageChange1(fileinput: any): void {
// console.log(fileinput)
console.log(fileinput.files)
// console.log(fileinput.files?.['0'])
// console.log(fileinput.files[0])

const file=fileinput.files[0];
this.productImage=fileinput.files[0];

if(file){
console.log(file)
const reader = new FileReader();
reader.readAsDataURL(file);
reader.onload = (e:any) =>{ 
    this.previewImage = e.target?.result;
    // console.log(e.target?.result)
  }
}

// if (fileInput.files && fileInput.files.length > 0) {
//   this.selectedFile = fileInput.files[0]; // Get the first selected file
//   console.log('Selected file:', this.selectedFile);
// }

}
// Set product rating
setRating(rating: number): void {
this.product.ratings = rating;
}



 
}
