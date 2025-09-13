import java.util.Scanner ;
class hotelRoomBooking{
    public static void main(String args[]){
        int floor=5;
        int roomsPerFloor=3;
        char [][]Array = new char[floor][roomsPerFloor];
        Scanner scan = new Scanner(System.in);
        int choice ;
        for (int i = 0; i<floor; i++) {
            for (int j = 0; j < roomsPerFloor; j++) {
                Array[i][j]='A';
            }
        }
        do{
            System.out.println("1.Room status ");
            System.out.println("2.Book room ");
            System.out.println("3. Exit. ");
            System.out.println("Enter your choice ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:{
                    for (int i = floor-1; i>=0; i--) {
                        for (int j = roomsPerFloor-1; j >=0 ; j--) {
                            System.out.print(Array[i][j]+ " ");
                        }
                    System.out.println(" ");
                    }
                } 
                break;
                case 2 : {
                    System.out.println("Enter floor Number ");
                    int yourFloor= scan.nextInt();
                    System.out.println("Enter room Number ");
                    int yourRoom= scan.nextInt();
                    if (Array[yourFloor-1][ yourRoom-1]=='B') {
                        System.out.println("Room is Already booked");                        
                    }
                    else {
                        Array[yourFloor-1][ yourRoom-1]= 'B';
                        System.out.println("Your Room is Booked");
                    }
                }
                break ;
                case 3 : {
                    System.out.println("Exiting .....");
                    break ;
                }
                    
                
            
                default:
                    System.out.println("invalid input ");
                    break;
            }
        }while(choice!=3);

    }
}