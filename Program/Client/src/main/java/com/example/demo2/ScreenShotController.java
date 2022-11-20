package com.example.demo2;

import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
//import java.awt.Image;
import java.awt.*;
//import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class ScreenShotController {

    BufferedImage image;

    @FXML
    private AnchorPane hhh;
    @FXML
    void CaptureScreen(ActionEvent event) throws IOException {
        HelloController.dout.writeUTF("CaptureScreen");
        HelloController.dout.flush();

        byte[] sizeAr = new byte[4];

        int size = 0;
        HelloController.din.read(sizeAr);
             size= ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        byte[] imageAr = new byte[size];


        HelloController.din.read(imageAr);

            image = ImageIO.read(new ByteArrayInputStream(imageAr));

        System.out.println("Received " + image.getHeight() + "x" + image.getWidth());
        ImageIO.write(image, "jpg", new File("screenshot.jpg"));

        InputStream stream = new FileInputStream("screenshot.jpg");
        Image image1 = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image1);
        //Setting the image view parameters
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);

        AnchorPane.setTopAnchor(imageView, 10.0);
        AnchorPane.setLeftAnchor(imageView, 10.0);
        AnchorPane.setRightAnchor(imageView, 10.0);
        AnchorPane.setBottomAnchor(imageView, 50.0);

        hhh.getChildren().add(imageView);
        int x=0;
        byte[] bytes=new byte[1024];
       while ((x=HelloController.din.read(bytes))==-1) {
           System.out.println("doc het");
       }
    }

    @FXML
    void DownloadPicture(ActionEvent event) throws IOException {
            if (image!=null) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save");
                fileChooser.setInitialDirectory(new File("D://"));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG Files", "*.jpg"));
                //Show save file dialog

                File file = fileChooser.showSaveDialog(HelloController.stageScreenshot);
                ImageIO.write(image,"JPG",file);

                image.flush();
            }
            else {
                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                alert1.setContentText("Picture is empty!");
                alert1.showAndWait();
            }
    }

}
