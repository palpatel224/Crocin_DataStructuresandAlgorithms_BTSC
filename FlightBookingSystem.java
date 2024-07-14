import java.util.*;

public class FlightBookingSystem {
    private Map<String, Queue<String>> seatLayout;
    private Map<String, String> reservations;
    private List<WaitlistEntry> waitlist;

    public FlightBookingSystem(Map<String, Queue<String>> seatLayout) {
        this.seatLayout = new HashMap<>(seatLayout);
        this.reservations = new HashMap<>();
        this.waitlist = new ArrayList<>();
    }

    public void initiateFlight() {
        System.out.println("Flight initialized with seats: " + seatLayout);
    }

    public void reserveSeat(String seatType, String userId) {
        if (seatLayout.containsKey(seatType) && !seatLayout.get(seatType).isEmpty()) {
            String seat = seatLayout.get(seatType).poll();
            reservations.put(userId, seat);
            System.out.println("Reserved " + seat + " for user " + userId);
        } else {
            waitlist.add(new WaitlistEntry(userId, seatType));
            System.out.println("User " + userId + " added to waitlist for " + seatType);
        }
    }

    public boolean checkSeatAvailability(String seatType) {
        return seatLayout.containsKey(seatType) && !seatLayout.get(seatType).isEmpty();
    }

    public String suggestOptimalSeat(List<String> preferences) {
        for (String pref : preferences) {
            if (checkSeatAvailability(pref)) {
                return pref;
            }
        }
        return null;
    }

    public void manageWaitlist() {
        Iterator<WaitlistEntry> iterator = waitlist.iterator();
        while (iterator.hasNext()) {
            WaitlistEntry entry = iterator.next();
            if (checkSeatAvailability(entry.getSeatType())) {
                reserveSeat(entry.getSeatType(), entry.getUserId());
                iterator.remove();
            }
        }
    }

    public void cancelReservation(String userId) {
        if (reservations.containsKey(userId)) {
            String seat = reservations.remove(userId);
            String seatType = getSeatType(seat);
            seatLayout.get(seatType).add(seat);
            System.out.println("Cancelled reservation for user " + userId);
            manageWaitlist();
        } else {
            System.out.println("No reservation found for user " + userId);
        }
    }

    public void changeReservation(String userId, String newSeatType) {
        cancelReservation(userId);
        reserveSeat(newSeatType, userId);
    }

    private String getSeatType(String seat) {
        for (Map.Entry<String, Queue<String>> entry : seatLayout.entrySet()) {
            if (entry.getValue().contains(seat)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static class WaitlistEntry {
        private final String userId;
        private final String seatType;

        public WaitlistEntry(String userId, String seatType) {
            this.userId = userId;
            this.seatType = seatType;
        }

        public String getUserId() {
            return userId;
        }

        public String getSeatType() {
            return seatType;
        }
    }

    public static void main(String[] args) {
        Map<String, Queue<String>> seatLayout = new HashMap<>();
        seatLayout.put("Aisle", new LinkedList<>(Arrays.asList("1A", "2A", "3A")));
        seatLayout.put("Window", new LinkedList<>(Arrays.asList("1W", "2W", "3W")));
        seatLayout.put("Middle", new LinkedList<>(Arrays.asList("1M", "2M", "3M")));

        FlightBookingSystem system = new FlightBookingSystem(seatLayout);
        system.initiateFlight();
        system.reserveSeat("Window", "User1");
        system.reserveSeat("Aisle", "User2");
        system.reserveSeat("Middle", "User3");
        System.out.println("Suggested seat: " + system.suggestOptimalSeat(Arrays.asList("Window", "Aisle", "Middle")));
        System.out.println("Is Window available? " + system.checkSeatAvailability("Window"));
        system.manageWaitlist();
        system.cancelReservation("User1");
        system.changeReservation("User2", "Window");
    }
}

