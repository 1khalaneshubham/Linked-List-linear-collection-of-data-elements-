public class RemoveCycleLinkedList {
    
    public static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){

        // step1 = create new Node
        Node newNode = new Node(data);
        size++;

        if(head == null){

            head = tail = newNode;
            return;
        }

        // step2 - newNode next = head
        newNode.next = head; // link

        // step3 - head = newNode
        head = newNode;
    }

    // Print kart data
    public void print(){

        if(head == null){
            System.out.println("LL is Empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data+" -> ");
            temp = temp.next;
        }
        System.out.println("Null");
    }

    public void add(int idx, int data){

        if(idx == 0){
            addFirst(data);
            return;
        }
        size++;

        Node newNode = new Node(data);
        Node temp = head;
        int i = 0;

        while (i < idx-1) {
            temp = temp.next;
            i++;
        }
        // i = idx - 1; temp -> prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public static boolean isCycle() { // floyd's cycle finding Algo
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
            if(slow == fast){
                return true; // cycle
            }
        }
        return false; // cycle doesn't exist
    }

    public static void removeCycle() {
        
        // detect cycle
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
                
        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
            if(slow == fast){
                cycle = true;
                break;
            }
        }
        if(cycle == false){
            return;
        }
        
        // find meeting point
        slow = head;
        Node prev = null;  // last Node store hoto
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
    
        // remove cycle -> last.next = null
        prev.next = null;
    }
    public static void main(String[] args) {
        
        head  = new Node(1);
        Node temp = new Node(2);
        head.next = temp; 
        head.next.next = new Node(3);
        head.next.next.next = temp;
        //1->2->3->2

        System.out.println(isCycle());
        removeCycle();        
        System.out.println("Rukoo jara sabar karo..");
        System.out.println(isCycle());
        
    }
}
