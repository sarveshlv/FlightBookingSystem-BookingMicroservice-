package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.booking.Model.FlightBooking;
import com.booking.Repository.BookingRepository;
import com.booking.Service.BookingService;

@SpringBootTest
class FlightBookingApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Test
	void testGetAllBookingHistory() {
		Mockito.when(bookingRepository.findAll()).thenReturn(Stream.of(
				 new FlightBooking(1,2,"July",2000,2000,"Economy",20),
				 new FlightBooking(2,3,"June",2000,2000,"Economy",21))
				 .collect(Collectors.toList()));
		assertEquals(2,bookingService.getAllBookingHistory().size());
	}
	
	@Test
	void testGetBookingByBookingId() {
		FlightBooking flight = new FlightBooking(2,3,"June",2000,2000,"Economy",21);
		Mockito.when(bookingRepository.findByBookingId(1)).thenReturn(flight);
		assertEquals(flight,bookingService.getBookingByBookingId(1));
	}
	
	@Test
	void testGetBookingByPassengerId() {
		FlightBooking flight = new FlightBooking(2,3,"June",2000,2000,"Economy",21);
		Mockito.when(bookingRepository.findByPassengerId(1)).thenReturn(flight);
		assertEquals(flight,bookingService.getBookingByBookingId(1));
	}

	@Test
	void testAddBooking() {
		FlightBooking flight = new FlightBooking(2,3,"June",2000,2000,"Economy",21);
		Mockito.when(bookingRepository.save(flight)).thenReturn(flight);
		assertEquals("Flight with ID "+flight.getFlightId()+" has been added.",
					 bookingService.addBooking(flight));
	}
	
	@Test
	void testDeleteAllBookingsHistory() {
		assertEquals("All the flight details are deleted.",
					 bookingService.deleteAllBookingsHistory());
	}
	
	@Test
	void testDeleteByBookingId() {
		FlightBooking flight = new FlightBooking(2,3,"June",2000,2000,"Economy",21);
		int flightId=flight.getFlightId();
		assertEquals("Flight with ID "+flightId+" has been deleted.",
					 bookingService.deleteByBookingId(1));
	}
}
