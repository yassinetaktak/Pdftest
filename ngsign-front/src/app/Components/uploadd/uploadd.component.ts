import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {UploadService} from "../../Services/upload.service";

@Component({
  selector: 'app-uploadd',
  templateUrl: './uploadd.component.html',
  styleUrls: ['./uploadd.component.css']
})
export class UploaddComponent implements OnInit {
  selectedFiles?: FileList;
  progress = 0;
  message = '';

  fileInfos?: Observable<any>;
  form: FormGroup;
  constructor(private uploadService: UploadService,
              public formbuilder: FormBuilder) {
    this.form = this.formbuilder.group({
      numPage: [''],
      cordX: [''],
      cordY: [''],
      pdf: [null],
      image: [null],
    });
  }


  ngOnInit(): void {
    this.getFiles();
  }

  // tslint:disable-next-line:typedef
  uploadFile(event) {
    const file = (event.target as HTMLInputElement).files[0];
    this.form.patchValue({
      pdf: file,
    });
    this.form.get('pdf').updateValueAndValidity();
  }
  // tslint:disable-next-line:typedef
  uploadFileImage(event) {
    const img = (event.target as HTMLInputElement).files[0];
    this.form.patchValue({
      image: img,
    });
    this.form.get('image').updateValueAndValidity();
  }

  // tslint:disable-next-line:typedef
  submitForm() {
    var formData: any = new FormData();
    formData.append('pdf', this.form.get('pdf').value);
    formData.append('image', this.form.get('image').value);
    formData.append('numPage', this.form.get('numPage').value);
    formData.append('cordX', this.form.get('cordX').value);
    formData.append('cordY', this.form.get('cordY').value);
    this.uploadService.upload(formData).subscribe(
      (event: any) => {
        this.getFiles();
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  // tslint:disable-next-line:typedef
  getFiles() {
    this.uploadService.getFiles().subscribe(
      (succes) => {
        this.fileInfos = succes;
        console.log('success :', succes);
      },
      (error) => console.log(error)
    );
  }
}
