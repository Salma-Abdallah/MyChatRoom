package gov.iti.jets.controllers;

public class FXMLControllerFactory {

    public static FXMLController getController(String controller){
        if(controller.equals("user-profile")){
            // return new UserProfileController();
        }else if(controller.equals("signup-page")){
            return new SignupPageFxmlController();
        }else if(controller.equals("main-alignment")){
            return new MainAlignmentController();
        // }else if(controller.equals("message")){
        //     return new MessageControllerFXML();
        }else if(controller.equals("side-bar")){
            return new SideBarController();
        }else if(controller.equals("default-content")){
            return new DefaultContentController();
        }else if(controller.equals("chatting-panel")){
            return new ChattingPanelController();
        }
        
        return null;
    }
}
