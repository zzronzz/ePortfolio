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

  // User-facing feedback messages
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private tripService: TripDataService) {}

  // Load trip data when editing
  ngOnInit() {
    const saved = localStorage.getItem('editTrip');

    if (saved) {
      this.trip = JSON.parse(saved);

      // Clean image field for display in input
      if (this.trip.image) {
        this.trip.image = this.trip.image.split('/').pop();
      }
    }
  }

  // Checks for missing or blank values
  private isBlank(value: any): boolean {
    return value === undefined || value === null || String(value).trim() === '';
  }

  // Validates required trip fields before sending data to the API
  private validateTrip(): boolean {
    if (
      this.isBlank(this.trip.code) ||
      this.isBlank(this.trip.name) ||
      this.isBlank(this.trip.length) ||
      this.isBlank(this.trip.resort) ||
      this.isBlank(this.trip.perPerson) ||
      this.isBlank(this.trip.image) ||
      this.isBlank(this.trip.description)
    ) {
      this.errorMessage = 'Please complete all required trip fields before submitting.';
      return false;
    }

    return true;
  }

  // Builds a clean trip object for the backend
  private buildTripPayload() {
    const imageName = String(this.trip.image).replace('/images/', '').trim();

    return {
      code: String(this.trip.code).trim(),
      name: String(this.trip.name).trim(),
      length: String(this.trip.length).trim(),
      start: new Date(),
      resort: String(this.trip.resort).trim(),
      perPerson: String(this.trip.perPerson).trim(),
      image: '/images/' + imageName,
      description: String(this.trip.description).trim()
    };
  }

  // Handles form submission for both Add and Update
  onSubmit() {
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.validateTrip()) {
      return;
    }

    const tripPayload = this.buildTripPayload();

    // If trip has _id, perform UPDATE
    if (this.trip._id) {
      this.tripService.updateTrip(this.trip._id, tripPayload).subscribe({
        next: () => {
          this.successMessage = 'Trip updated successfully.';
          alert('Trip updated successfully.');
          localStorage.removeItem('editTrip');

          window.location.reload();
        },
        error: (err) => {
          console.error('UPDATE ERROR:', err);
          this.errorMessage = 'Unable to update the trip. Please check the information and try again.';
        }
      });
    }

    // Otherwise perform ADD
    else {
      this.tripService.addTrip(tripPayload).subscribe({
        next: () => {
          this.successMessage = 'Trip added successfully.';
          alert('Trip added successfully.');

          window.location.reload();
        },
        error: (err) => {
          console.error('POST ERROR:', err);
          this.errorMessage = 'Unable to add the trip. Please check the information and try again.';
        }
      });
    }
  }
}