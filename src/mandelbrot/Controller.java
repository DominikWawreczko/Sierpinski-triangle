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



public class Controller {
               public Canvas canvas;
               public Label label;
               private GraphicsContext gc;

               Complex p1,p2,p3;
               private Boolean sprawdzacz = false;
               private Boolean t1 = false;
               private Boolean t2 = false;
               private Boolean t3 = false;
               final int size = 512;
               WritableImage wr = new WritableImage(size, size);
               PixelWriter pw = wr.getPixelWriter();


               public void initialize() {
                   gc = canvas.getGraphicsContext2D();
                   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
               }

               public void mouseMoves(MouseEvent mouseEvent) {
               }

               public void drawTriangle(Complex p1, Complex p2, Complex p3) {
                   final int iterationsInSierpinskiTriangle = 20000;

                   CollinearChecker test = new CollinearChecker(p1,p2,p3);
                   Complex gamepoint=new Complex(FindPointInsideTriangle.findUsingInterceptTheorem(p1,p2,p3)); //(((p1.getRe()+p2.getRe()+p3.getRe())/2),(p1.getIm()+p2.getIm()+p3.getIm())/2);
                   System.out.println("Przed if");
                   System.out.println(p3.getIm());

                   if (test.checkIfTheyAreNotCollinear() == true) {//czyli nie są wspólniniowe
                       System.out.println("Przed pętlą");
                       System.out.println(p3.getIm());
                       double s = Math.random();


                       for (int i = 0; i < iterationsInSierpinskiTriangle; i++) {
                           s = Math.random();
                           Complex p;
                           if (s <= 1.0/3.0) {
                               p = p1;
                           } else if (s <= 2.0/3.0) {
                               p=p2;
                       } else {
                               p=p3;
                           }
                           gamepoint.add(p);
                           int a , b;

                           gamepoint.setRe((gamepoint.getRe())/2);
                          gamepoint.setIm((gamepoint.getIm())/2);

                           a=(int)gamepoint.getRe();
                           b=(int)gamepoint.getIm();
                           pw.setArgb((int) gamepoint.getRe(), (int) gamepoint.getIm(), 0xFFFF00FF);
                           System.out.println("Rysuje");



                       }
                       System.out.println("Przed malowaniem");
                       System.out.println(p3.getIm());
                       gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                       gc.drawImage(wr, 0, 0, 512, 512);
                   }
                   t1 = false;
                   t2 = false;
                   t3 = false;
               }


               public void mousePressed(MouseEvent mouseEvent) {
                   if (sprawdzacz == true) {//czyli tylko wtedy gdy chcemy sami robić trójkąt
                       if (t1 == false) {
                         p1 = new   Complex((double)mouseEvent.getX() , (double) mouseEvent.getY());
                       //  System.out.println(p1.toString());

                           t1 = true;

                       } else if (t2 == false) {
                           p2 = new   Complex((double)mouseEvent.getX() , (double) mouseEvent.getY());
                           t2 = true;
                         //  System.out.println(p2.toString());


                       } else if (t3 == false) {
                           p3 = new   Complex((double)mouseEvent.getX() , (double) mouseEvent.getY());
                           t3 = true;
                           System.out.println("YTEST");
                           System.out.println(p3.getIm());
                           System.out.println(mouseEvent.getY());


                           drawTriangle(p1,p2,p3);
                           System.out.println("Po trójkącie");
                         //  System.out.println(p3.toString());
                           System.out.println(p3.getIm());

                       }
               }

           }

               public void mouseReleased(MouseEvent mouseEvent) {
               }

               public void clearCanvas(ActionEvent actionEvent) {
                   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                //   gc.closePath();
                   gc.beginPath();

               }

               public void drawRect(ActionEvent actionEvent) {
                   gc.setStroke(Color.web("#FFF0F0"));//ustawia jaki kolor ma mieć krawędź
                   gc.setGlobalBlendMode(BlendMode.SRC_OVER);//określa jak zachowują się dwie rzeczy np kwadraty jak na siebie zachodzą
                   //tutaj mieszają się ich kolory BlendMode to enum zaś Multiply to jego stała która mówi że kolory mają się mieszać
                   gc.strokeRect(100.5, 100.5, 200, 200);
               }

               public void draw(ActionEvent actionEvent) {



                   for (int x = 0; x < size; x++) {
                       for (int y = 0; y < size; y++) {
                           pw.setArgb(x, y, (x & y) == 0 ? 0xFFFF00FF : 0xFFFFFFFF);    // Rysuje trójkąt Sierpińskiego
                       }
                   }

                   gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                   gc.drawImage(wr, 0, 0, 512, 512);
               }

public static int x=9;

               public void sayHello(ActionEvent actionEvent) {
                   if(x==9){x++;
                       label.setText("Hello");
                   }else {
                       label.setText("Paaaa");
                   }
               }

               public void drawYourRect(ActionEvent actionEvent) {
                   sayHello(actionEvent);
                   if (sprawdzacz == false) {//czyli przycisk nie jest uruchomiony wiec trzeba go uruchomić
                       sprawdzacz=true;
                       for (int x = 0; x < size; x++) {
                           for (int y = 0; y < size; y++) {
                               pw.setArgb(x, y, 0xFFFFFFFF);

                           }
                       }

                       gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                       gc.drawImage(wr, 0, 0, 512, 512);


                   }
                   else {
                       sprawdzacz=false;//czyli wyłączamy rysowanie

                       gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                   //    gc.closePath();
                    //   gc.beginPath();
                       t1=false;t2=false;t3=false;


                   }
               }
           }