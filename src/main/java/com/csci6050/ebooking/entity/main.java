package com.csci6050.ebooking.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class main {

    public static void main(String[] args) {
        try {
            String fileName = "C://Users//Sean//IdeaProjects//ebooking//src//main//java//com//csci6050//ebooking//images//[[name]]";
            fileName = fileName.replace("[[name]]", "test.jpg");
            File file = new File(fileName);
            BufferedImage bufferedImage = ImageIO.read(file);

            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            JFrame jFrame = new JFrame();

            jFrame.setLayout(new FlowLayout());

            jFrame.setSize(500, 500);
            JLabel jLabel = new JLabel();

            jLabel.setIcon(imageIcon);
            jFrame.add(jLabel);
            jFrame.setVisible(true);

            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            Image picture = ImageIO.read(new File("C://Users//Sean//IdeaProjects//ebooking//src//main//java//com//csci6050//ebooking//images//test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}