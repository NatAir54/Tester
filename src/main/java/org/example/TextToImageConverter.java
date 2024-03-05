package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextToImageConverter {

    public static void main(String[] args) {
        String text = "Text from that html_format column";
        String outputFilePath = "C:\\Users\\User\\Documents\\java\\Tester\\src\\main\\resources\\images\\output.png";

        BufferedImage image = createImageFromString(text);
        saveImageAsPng(image, outputFilePath);
        System.out.println("Image created successfully.");

    }

    private static BufferedImage createImageFromString(String text) {

        // Set the dimensions of the image (width and height) and initialize a BufferedImage object
        int width = 800;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object from the BufferedImage, which allows to draw on the image
        Graphics2D g2d = image.createGraphics();

        // Set the background color of the image to white and fill the entire image with that color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Set the color and font for the text that will be drawn on the image
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));

        // FontMetrics is used to determine the dimensions of text
        FontMetrics fm = g2d.getFontMetrics();

        // stringWidth(text) calculates the width of the text in pixels.
        int textWidth = fm.stringWidth(text);

        //  x is calculated to horizontally center the text in the image
        int x = (width - textWidth) / 2;

        // y is calculated to vertically center the text in the image, accounting for ascent
        int y = (height - fm.getHeight()) / 2 + fm.getAscent();

        // Draw the specified text onto the image at the specified coordinates (x, y)
        g2d.drawString(text, x, y);

        // Release any system resources used by the Graphics2D context
        g2d.dispose();

        return image;
    }

    private static void saveImageAsPng(BufferedImage image, String filePath) {
        try {

            File outputFile = new File(filePath);

            // Write the BufferedImage to the file in the png format using the ImageIO class
            ImageIO.write(image, "png", outputFile);
            System.out.println("File saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving image as png: " + e.getMessage());
        }
    }

}
