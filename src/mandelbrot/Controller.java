package mandelbrot;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.lang.Math;

/*
FOR ACADEMIC REASONS I'M USING COMPLEX NUMBERS AS POINTS OBJECTS!
 */

public class Controller {
               public Canvas canvas;
               public Label label;
               private GraphicsContext gc;

               Complex triangleFirstVertexParam, triangleSecondVertexParam, triangleThirdVertexParam;
               private Boolean drawingOwnTriangleMode;
               private Boolean drawingFirstVertex;
               private Boolean drawingSecondVertex;
               private Boolean drawingThirdVertex;
               final int size = 512;
               int iterationsInSierpinskiTriangle;
               WritableImage wr ;
               PixelWriter pw;


               public void initialize() {
                   iterationsInSierpinskiTriangle  = 20000;
                   wr = new WritableImage(size, size);
                   pw = wr.getPixelWriter();
                   drawingFirstVertex = false;
                   drawingSecondVertex = false;
                   drawingThirdVertex = false;
                   drawingOwnTriangleMode = false;
                   gc = canvas.getGraphicsContext2D();
                   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
               }

               public void mouseMoves(MouseEvent mouseEvent) {
               }

               public void drawTriangle(Complex p1, Complex p2, Complex p3) {
                   CollinearChecker collinearChecker = new CollinearChecker(p1,p2,p3);
                   Complex gamepoint = new Complex(FindRandomPointInsideTriangle.findUsingInterceptTheorem(p1,p2,p3));
                   if (collinearChecker.checkIfTheyAreNotCollinear() == true) {
                       double randomNumber = Math.random();
                       for (int i = 0; i < iterationsInSierpinskiTriangle; i++) {
                           randomNumber = Math.random();
                           Complex p;
                           if (randomNumber <= 1.0/3.0) {
                               p = p1;
                           } else if (randomNumber <= 2.0/3.0) {
                               p=p2;
                           } else {
                               p=p3;
                           }
                           gamepoint.add(p);
                           int a , b;
                           gamepoint.setRe((gamepoint.getRe())/2);
                           gamepoint.setIm((gamepoint.getIm())/2);
                           pw.setArgb((int) gamepoint.getRe(), (int) gamepoint.getIm(), 0xFFFF00FF);
                       }
                       gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                       gc.drawImage(wr, 0, 0, 512, 512);
                   }
                   drawingFirstVertex = false;
                   drawingSecondVertex = false;
                   drawingThirdVertex = false;
               }


               public void mousePressed(MouseEvent mouseEvent) {
                   if (drawingOwnTriangleMode == true) {
                       if (drawingFirstVertex == false) {
                           setVertexParams(mouseEvent, triangleFirstVertexParam);
                           drawingFirstVertex = true;

                       } else if (drawingSecondVertex == false) {
                           setVertexParams(mouseEvent, triangleSecondVertexParam);
                           drawingSecondVertex = true;
                       } else if (drawingThirdVertex == false) {
                           setVertexParams(mouseEvent, triangleFirstVertexParam);
                           drawingThirdVertex = true;
                          drawTriangle(triangleFirstVertexParam, triangleSecondVertexParam, triangleThirdVertexParam);
                       }
                   }
               }

           public void setVertexParams(MouseEvent mouseEvent, Complex vertex){
               vertex.setRe((double)mouseEvent.getX());
               vertex.setIm((double)mouseEvent.getY());
           }

           public void mouseReleased(MouseEvent mouseEvent) {
           }

           public void clearCanvas(ActionEvent actionEvent) {
                   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                   gc.beginPath();
               }

           public void drawRect(ActionEvent actionEvent) {
                gc.setStroke(Color.web("#FFF0F0"));
                gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                gc.strokeRect(100.5, 100.5, 200, 200);
           }

           public void draw(ActionEvent actionEvent) {
                setSierpinskiTrianglePixels(SierpinskiTriangle.DEFAULT);
                gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                gc.drawImage(wr, 0, 0, 512, 512);
           }

           public void drawYourRect(ActionEvent actionEvent) {
               if (!drawingOwnTriangleMode) {
                   drawingOwnTriangleMode =true;
                   setSierpinskiTrianglePixels(SierpinskiTriangle.OWN);
                   gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                   gc.drawImage(wr, 0, 0, 512, 512);
                   }
               else {
                   drawingOwnTriangleMode =false;
                   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                   drawingFirstVertex =false;
                   drawingSecondVertex =false;
                   drawingThirdVertex =false;


                   }
               }


           private void setSierpinskiTrianglePixels(SierpinskiTriangle sierpinskiTriangle){
                   for (int x = 0; x < size; x++) {
                        for (int y = 0; y < size; y++) {
                            if (sierpinskiTriangle.equals(SierpinskiTriangle.OWN)) {
                                pw.setArgb(x, y, 0xFFFFFFFF);
                            }else {
                                pw.setArgb(x, y, (x & y) == 0 ? 0xFFFF00FF : 0xFFFFFFFF);
                            }

                        }
                    }
                }



           }