import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  onSubmit(contactForm: NgForm) {
    if (contactForm.valid) {
      const contactData = contactForm.value;
      console.log('Contact Form Submitted:', contactData);
      alert('Your message has been submitted successfully!');
      contactForm.reset(); // Clear the form after submission
    }
  }
}
