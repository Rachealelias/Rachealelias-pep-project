package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import javafx.scene.shape.Path;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
       /* app.post("/login", this::postLoginAccountHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.put("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromUserGivenAccountHandler);
    */
        return app;
    }

        private void postAccountHandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account registeredAccount = accountService.addAccount(account);
    
                 if (registeredAccount.getUsername() == null || registeredAccount.getUsername().trim().isEmpty()){
                    ctx.status(400);
                }
                if (registeredAccount.getPassword() == null || registeredAccount.getPassword().length() < 4) {
                    ctx.status(400);
                }
                if (registeredAccount.existsByUsername(registeredAccount.getUsername())) {
                    ctx.status(400);
                }else{
                    ctx.json(mapper.writeValueAsString(registeredAccount));
                } 

       /*  private void postAccountHandler(Context ctx) throws JsonProcessingException {
            ObjectMaper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account registeredAccount = accountService.registerAccount(account);
            Account registeredAccount = accountService.registerAccount(account);
        if (registeredAccount != null) {
            ctx.json(mapper.writeValueAsString(registeredAccount));
        } else {
            ctx.status(400);
        }
             
    */
        }
        private void postLoginAccountHandler(Context  ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account loggedAccount = accountService.addAccount(account);
            if (loggedAccount == null) {
                ctx.status(400);
            }else{
                ctx.json(mapper.writeValueAsString(loggedAccount)); 
            }

        }
        private void postMessageHandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(ctx.body(), Message.class);
            Message addedMessage = messageService.addMessage(message);
            if(addedMessage!=null){
                ctx.json(mapper.writeValueAsString(addedMessage));
            }else{
                ctx.status(400);
            }
        }
        private void getAllMessagesHandler(Context ctx) {
            List<Message> messages = messageService.getAllMessages();
            ctx.json(messages);
        }
        private void updateMessageHandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(ctx.body(), Message.class);
            int message_id = Integer.parseInt(ctx.pathParam("message_id"));
            Message updatedMessage = messageService.updateMessage(message_id, message);
            System.out.println(updatedMessage);
            if(updatedMessage == null){
                ctx.status(400);
            }else{
                ctx.json(mapper.writeValueAsString(updatedMessage));
            }
    
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}