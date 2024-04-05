import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Scanner;

public class MyTravelPlanner {


	    public String getPlan(LocalDate date, String weather, LocalTime firstAppointment, LocalTime lastAppointment) {
	        if (isWeekend(date)) {
	            if (isGoodWeather(weather) && canTakeTrainOnWeekend()) {
	                return "Please take the train to go to the city, and train to get back home on " + date.toString() + ".";
	            } else {
	                return "Please drive on " + date.toString() + ", and leave the house at least an hour before your first appointment.";
	            }
	        } else {
	            if (isGoodWeather(weather) && canTakeTrainOnWeekday(firstAppointment, lastAppointment)) {
	                return "Please take the train to go to the city and train to get back home on " + date.toString() + ".";
	            } else {
	                return "Please drive on " + date.toString() + ", and leave the house at least an hour before your first appointment.";
	            }
	        }

	    }

	    private boolean canTakeTrainOnWeekday(LocalTime firstAppointment, LocalTime lastAppointment) {
	        LocalTime firstTrain = LocalTime.of(6, 0);
	        LocalTime lastTrain = LocalTime.of(23, 0);
	        return true; 
	    }
	    // determining day of week
	    private boolean isWeekend(LocalDate date) {
	        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	    }
	    // determining weather type
	    private boolean isGoodWeather(String weather) {
	        return !weather.equalsIgnoreCase("Rainy") && !weather.equalsIgnoreCase("Snowy");
	    }

	    private boolean canTakeTrainOnWeekend() {
	        LocalTime firstTrain = LocalTime.of(10, 0);
	        LocalTime lastTrain = LocalTime.of(22, 0);

	        return LocalTime.now().isAfter(firstTrain) && LocalTime.now().isBefore(lastTrain);
	    }

	    // factors to consider
	    public static void main(String[] args) {
	        MyTravelPlanner planner = new MyTravelPlanner();
	        Scanner input = new Scanner(System.in);

	        LocalDate date;
	        String weather;
	        LocalTime firstAppointment;
	        LocalTime lastAppointment;

	        do {
	            System.out.println("Enter the date (YYYY-MM-DD): ");
	            try {
	                date = LocalDate.parse(input.nextLine());
	                break;
	            } catch (Exception e) {
	                System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
	            }
	        } while (true);

	        System.out.println("Enter the weather: ");
	        weather = input.nextLine();

	        System.out.println("Enter the first appointment time (HH:MM): ");
	        firstAppointment = LocalTime.parse(input.nextLine());

	        System.out.println("Enter the last appointment time (HH:MM): ");
	        lastAppointment = LocalTime.parse(input.nextLine());

	        if (lastAppointment.isBefore(firstAppointment)) {
	            System.out.println("Last appointment time should be after first appointment time.");
	            return;
	        }

	        String plan = planner.getPlan(date, weather, firstAppointment, lastAppointment);
	        System.out.println(plan);
	    }
	}


