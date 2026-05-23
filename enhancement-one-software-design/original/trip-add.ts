import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TripDataService } from '../services/trip-data';

// Component used for both adding and editing trips
@Component({
  selector: 'app-trip-add',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './trip-add.html',
  styleUrls: ['./trip-add.css']
})
export class TripAdd implements OnInit {

  // Holds form data for a trip
  trip: any = {};

  constructor(private tripService: TripDataService) {}

  // Load trip data when editing
  // Retrieves selected trip from localStorage and populates form
  ngOnInit() {
    const saved = localStorage.getItem('editTrip');

    if (saved) {
      this.trip = JSON.parse(saved);

      // Clean image field for display in input (remove path)
      if (this.trip.image) {
        this.trip.image = this.trip.image.split('/').pop();
      }
    }
  }

  // Handles form submission for both Add and Update
  onSubmit() {

    // Build trip object to send to backend
    const newTrip = {
      code: String(this.trip.code),
      name: String(this.trip.name),
      length: String(this.trip.length),
      start: new Date(), // current date used for start
      resort: String(this.trip.resort),
      perPerson: String(this.trip.perPerson),
      image: '/images/' + String(this.trip.image).replace('/images/', '').trim(),
      description: String(this.trip.description)
    };

    console.log("FINAL PAYLOAD:", newTrip);

    // If trip has _id, perform UPDATE (PUT)
    if (this.trip._id) {
      this.tripService.updateTrip(this.trip._id, newTrip).subscribe({
        next: () => {
          alert('Trip updated');
          localStorage.removeItem('editTrip'); // clear edit state
          window.location.reload(); // refresh list
        },
        error: (err) => {
          console.error("UPDATE ERROR:", err);
        }
      });
    } 
    // Otherwise perform ADD (POST)
    else {
      this.tripService.addTrip(newTrip).subscribe({
        next: () => {
          alert('Trip added');
          window.location.reload(); // refresh list
        },
        error: (err) => {
          console.error("POST ERROR:", err);
        }
      });
    }
  }
}