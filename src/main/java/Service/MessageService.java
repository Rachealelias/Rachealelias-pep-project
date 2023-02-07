package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        if(message.message_text.isEmpty() || message.message_text.length() < 255) return null;
        return messageDAO.creatMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message updateMessage(int message_id, Message message){
        Message messageFromDb = this.messageDAO.getMessageById(message_id);

        if(messageFromDb == null || message.message_text.length() > 255 || message.message_text.isEmpty()) return null;
          messageDAO.updateMessage(message_id, message);
        return this.messageDAO.getMessageById(message_id);
    }
    
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);

    }
    
    public Message deleteMessage(int message_id) {
        return messageDAO.deleteMessage(message_id);
        }
        
         
    public List<Message> getMessagesByAccountId(int posted_by) {
        return messageDAO.getAllMessagesFromUserGivenAccount(posted_by);
    }
    
}
