import java.util.*;
public class FlightBookingSystem{
    class seatInfo{
        String seatNo ;
        String avl;
    }
    public static seatInfo[][] seats(int n){
        seatInfo[][] seat = new seatInfo[n][6];
        for(int i=0;i<n;i++){
            for(int j=0;j<6;j++){
                if(j==0||j==5){
                    seat[i][j].avl = "A";
                    seat[i][j].seatNo = "W"+(i+1);
                }
                else if(j==1||j==4){
                    seat[i][j].avl = "A";
                    seat[i][j].seatNo = "M"+(i+1);
                }
                else{
                    seat[i][j].avl = "A";
                    seat[i][j].seatNo = "A"+(i+1);
                }
            }
        }
        return seat;
    }
    public static void display(seatInfo[][] seat){
        for(int i=0;i<seat.length;i++){
            for(int j=0;j<6;j++){
                System.out.print(seat[i][j].seatNo+" "+seat[i][j].avl+" ");
            }
            System.out.println();
        }
    }
    public static void book(seatInfo[][] seat,String seatNo){
        for(int i=0;i<seat.length;i++){
            for(int j=0;j<6;j++){
                if(seat[i][j].seatNo.equals(seatNo)){
                    seat[i][j].avl = "B";
                    return;
                }
            }
        }
    }
    public static void cancel(seatInfo[][] seat,String seatNo){
        for(int i=0;i<seat.length;i++){
            for(int j=0;j<6;j++){
                if(seat[i][j].seatNo.equals(seatNo)){
                    seat[i][j].avl = "A";
                    return;
                }
            }
        }
    }
    public static void changeSeat(seatInfo[][] seat,String seatNo){
        for(int i=0;i<seat.length;i++){
            for(int j=0;j<6;j++){
                if(seat[i][j].seatNo.equals(seatNo)){
                    if(seat[i][j].avl.equals("A")){
                        seat[i][j].avl = "B";
                    }
                    else{
                        seat[i][j].avl = "Seat if Already Booked";
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        seatInfo[][] seat = seats(7);
        display(seat);
        System.out.println("W = Window, M = Middle, A = Aisle");
        System.out.println("A = Available, B = Booked");
        System.out.println("Press the repsective key to perform the operation");
        System.out.println("1. Book a seat");
        System.out.println("2. Cancel a seat");
        System.out.println("3. Change a seat");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        String seatNo = new String();
        switch(choice){
            case 1:
                System.out.println("Enter the seat number to book");
                seatNo = sc.next();
                book(seat,seatNo);
                display(seat);
                break;
            case 2:
                System.out.println("Enter the seat number to cancel");
                seatNo = sc.next();
                cancel(seat,seatNo);
                display(seat);
                break;
            case 3:
                System.out.println("Enter the seat number to change");
                seatNo = sc.next();
                changeSeat(seat,seatNo);
                display(seat);
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
}
