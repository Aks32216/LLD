package Behavioural_Design_Pattern.State_Design_Pattern;

class Document{
    private StringBuilder content;
    private DocumentState currentState;

    Document(){
        this.currentState=new DraftState();
        this.content=new StringBuilder("");
    }

    public void setCurrentState(DocumentState documentState){
        this.currentState=documentState;
    }

    public void appendContent(String content){
        this.content.append(content);
    }

    public String getContent() {
        return content.toString();
    }

    public void edit(String content){
        if(content.isBlank()){
            System.out.println("Content Empty. Cannot edit");
            return;
        }
        currentState.edit(this, content);
    }

    public void submit(){
        if(this.getContent().isBlank()){
            System.out.println("Content Empty. Cannot Submit for review");
            return;
        }
        currentState.submitReview(this);
    }

    public void reject(){
        currentState.reject(this);
    }

    public void approve(){
        currentState.approve(this);
    }

    public void unpublish(){
        currentState.unpublish(this);
    }
}

interface DocumentState{
    void edit(Document document,String content);
    void submitReview(Document document);
    void reject(Document document);
    void approve(Document document);
    void unpublish(Document document);
}

class DraftState implements DocumentState{
    @Override
    public void edit(Document document, String content) {
        System.out.println("Editing document, adding content: "+content);
        document.appendContent(content);
    }

    @Override
    public void submitReview(Document document) {
        System.out.println("Submitting Document with content: "+document.getContent()+" for review.");
        document.setCurrentState(new InReviewState());
    }

    @Override
    public void reject(Document document) {
        System.out.println("Cannot Reject document in draft state");
    }

    @Override
    public void approve(Document document) {
        System.out.println("Cannot approve document in draft state");
    }

    @Override
    public void unpublish(Document document) {
        System.out.println("Cannot Unpublish document in draft state");
    }
}

class InReviewState implements DocumentState{
    @Override
    public void edit(Document document, String content) {
        System.out.println("Cannot Edit document in review state");
    }

    @Override
    public void submitReview(Document document) {
        System.out.println("Document already for review. Wait for approver to take action");
    }

    @Override
    public void reject(Document document) {
        System.out.println("Docment rejected. Moving it to draft state to make changes");
        document.setCurrentState(new DraftState());
    }

    @Override
    public void approve(Document document) {
        System.out.println("Congratulations! Document has been approved by approver. Moving it to published state");
        document.setCurrentState(new PublishState());
    }

    @Override
    public void unpublish(Document document) {
        System.out.println("Cannot Unpublish document in review state");
    }
}

class PublishState implements DocumentState{
    @Override
    public void edit(Document document, String content) {
        System.out.println("Cannot Edit a published document. Unpublish it and then edit.");
    }

    @Override
    public void submitReview(Document document) {
        System.out.println("Document already published.");
    }

    @Override
    public void reject(Document document) {
        System.out.println("Document already published.");
    }

    @Override
    public void approve(Document document) {
        System.out.println("Document already approved");
    }

    @Override
    public void unpublish(Document document) {
        System.out.println("Moving published document to Draft state, to make some changes.");
        document.setCurrentState(new DraftState());
    }
}

public class Document_Workflow {
    public static void main(String[] args) {
        Document document=new Document();
        document.edit("Hi this is my first document.");
        document.edit("I will continue writing more about my idea");
        document.submit();
        document.approve();
    }
}
