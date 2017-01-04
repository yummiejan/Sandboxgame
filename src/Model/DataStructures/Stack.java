package Model.DataStructures;

/**
 * Created by aos on 07.11.2016.
 */
public class Stack<ContentType> {

    /* ----------- Anfang der inneren Klasse ----------- */
    private class StackNode{
        private ContentType content = null;
        private StackNode nextNode = null;

        public StackNode(ContentType content){
            this.content = content;
        }

        public StackNode getNext(){
            return nextNode;
        }

        public void setNext(StackNode next){
            this.nextNode = next;
        }

        public ContentType getContent(){
            return content;
        }
    }
    /* ----------- Ende der inneren Klasse ----------- */

    private StackNode head;
    private int size;

    public Stack(){
        head = null;
        size = 0;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void push(ContentType content){
        StackNode newNode = new StackNode(content);
        if(!isEmpty()){
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    public void pop(){
        if(!isEmpty()) {
            head = head.getNext();
        }
        size--;
    }

    public ContentType top(){
        if(!isEmpty()) {
            return head.getContent();
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}
