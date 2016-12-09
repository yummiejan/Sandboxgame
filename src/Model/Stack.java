package Model;

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

    public Stack(){
        head = null;
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
    }

    public void pop(){
        if(!isEmpty()) {
            head = head.getNext();
        }
    }

    public ContentType top(){
        if(!isEmpty()) {
            return head.getContent();
        }
        return null;
    }
}
