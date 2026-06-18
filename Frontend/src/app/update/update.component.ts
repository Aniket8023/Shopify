import { Component } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ConditionalExpr } from '@angular/compiler';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent {

  constructor(private service:HttpService,private router:Router, private route:ActivatedRoute){}

  ngOnInit():void{
    this.getDataFromUrl()
  }

  getDataFromUrl(){
    this.route.paramMap.
    subscribe((param:any)=>{
      let id = param.get("id");
      console.log(id);
      this.getDataFromBackend(id)
    })
  }

  getDataFromBackend(id:any){
    this.service.getParticularDataById(id)
    .subscribe((response:any)=>{
      this.product = response;
      console.log(this.product)
    })
  }

  product = {
    productTitle: '',
    originalPrice: null,
    description: '',
    productCategory: '',
    discountPercentage: null,
    sellingPrice:null,
    productQuantity: null,
    ratings: 0,
    productImagePath:''
    
    };
    previewImage: string | ArrayBuffer | null|undefined = null;
    productImage!:File;
    isImageSelect:boolean=false;


    onSubmit(form: any,inputFile:any){
      if (form.valid) {
      //console.log('Product Added:', this.product);
      
      this.service.updateProductData(this.product,this.productImage)
      .subscribe((response:any)=>{
        console.log(response);
        this.router.navigate(['/adminDashboard'])
      })
      
      
      // alert('Product added successfully!');
      form.reset();
      this.previewImage = null; // Clear the image preview      
      inputFile.value='';
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
        this.isImageSelect=true
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
