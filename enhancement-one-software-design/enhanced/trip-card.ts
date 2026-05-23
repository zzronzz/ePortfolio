import { Component, Input } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { TripDataService } from '../services/trip-data';

// Component responsible for displaying individual trip cards
@Component({
  selector: 'app-trip-card',
  standalone: true,
  imports: [CommonModule, CurrencyPipe],
  templateUrl: './trip-card.html',
  styleUrls: ['./trip-card.css']
})
export class TripCard {

  // Input property receives trip data from parent component
  @Input() trip!: any;

  constructor(private tripService: TripDataService) {}

  // Trigger edit mode for selected trip
  // Stores trip data in localStorage and reloads page
  editTrip(trip: any) {
    localStorage.setItem('editTrip', JSON.stringify(trip));
    window.location.reload();
  }
  
  // Delete selected trip by ID
  // Calls service to remove trip from database and refreshes list
  deleteTrip(id: string) {
    this.tripService.deleteTrip(id).subscribe({
      next: () => {
        alert('Trip deleted successfully.');
        window.location.reload();
      },
      error: (err) => {
        console.error('DELETE ERROR:', err);
        alert('Unable to delete the trip. Please try again.');
      }
    });
  }
}