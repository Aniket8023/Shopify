import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginComponent } from './login/login.component';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
    

  baseUrl:string="http://localhost:8080/api/";
  userBaseUrl:string='http://localhost:8080/user/';
 // getParticularDataById: any;

  constructor(private http:HttpClient) { }

  getProductData(){
   return (this.http.get(`${this.baseUrl}get-all`));
  }

  sigupUser(obj:any){
    return (this.http.post(`${this.userBaseUrl}register`,obj,{
      responseType:'text'
    }));
  }

  login(obj:any){
   return (this.http.post(`${this.baseUrl}user/login`,obj))
  }

  PostProductData(product: any, productImage: File) {
  const formData = new FormData();

  formData.append('productTitle', product.productTitle);
  formData.append('originalPrice', product.originalPrice.toString());
  formData.append('discountPercentage', product.discountPercentage.toString());
  formData.append('description', product.description);
  formData.append('productQuantity', product.productQuantity.toString());
  formData.append('productCategory', product.productCategory);
  formData.append('sellingPrice', product.sellingPrice.toString()); // ✅ Add this line
  formData.append('image', productImage);

  return this.http.post('http://localhost:8080/api/create', formData, {
    responseType: 'text' as 'json'
  });
}



    getParticularDataById(id:any){
      return this.http.get(`${this.baseUrl}get/${id}`);
    }

  deleteData(id:any){
    return (this.http.delete(`${this.baseUrl}delete/${id}`,{
      responseType:"text"
    }));
  }

  updateProductData(productObj:any,image:File){
    const formData = new FormData();
    formData.append("product",JSON.stringify(productObj));
    if(image){
      formData.append("image",image);
    } 

    return this.http.put(`${this.baseUrl}update`,formData,{responseType:"text"});

    
  }

}