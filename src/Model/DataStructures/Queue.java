package Model.DataStructures;

/**
 * Created by 204g01 on 31.10.2016.
 */
public class Queue<ContentType> {

    private QueueNode head;
    private QueueNode tail;

    /* -------------------- Anfang der inneren Klasse ---------------- */
    private class QueueNode{
        ContentType content = null;
        QueueNode nextNode = null;
        public QueueNode (ContentType content){
            this.content = content;
        }
        public void setNext(QueueNode next){
            nextNode = next;
        }
        public QueueNode getNext(){
            return nextNode;
        }
        public ContentType getContent(){
            return content;
        }
    }
    /* Ende*/

    public Queue(){
        head = null;
        tail = null;
    }

    public void enqueue(ContentType c){
        QueueNode node = new QueueNode(c);
        if (isEmpty()){
            head = node;
            tail = node;
        }else{
            tail.setNext(node);
            tail = node;
        }

    }
    public void dequeue(){
        if (head != tail ){
            head = head.getNext();
        }else{
            head = null;
            tail = null;
        }
    }
    public boolean isEmpty(){
        return head == null;
    }
    public ContentType front(){
        if (!isEmpty()) {
            return head.getContent();
        }else{
            return null;
        }
    }
}
