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


           //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^66
/*
*

Sometimes the clearRect(x, y, width, height) method on the canvas context might not erase the previous graphics drawn.
* This usually happens when we’re drawing paths using methods like lineTo(), arc(), rect(), etc.
* and then stroking them with stroke() or filling their content area using fill(). Here’s an example of what
* I’m trying to convey.

The solution to this problem is actually quite simple but the issue itself can cause major headache
* if you’re unable to nail it. We basically have to call the beginPath() method on the 2D context.
*  This method will create a new path and all the drawing functions called later will be directed to it.
* If we do not call beginPath() (and then preferably closing that using closePath()), then all the drawing commands
*  called will stack up in the memory and every time stroke() or fill() is called,
*  it’ll draw all the graphic paths.*/
public class Controller {
               public Canvas canvas;
               public Label label;
               private GraphicsContext gc;
               private double x1;
               private double x2;
               private double x3;
               private double y1;
               private double y2;
               private double y3;
               Complex p1,p2,p3;
               private Boolean sprawdzacz = false;
               private Boolean t1 = false;
               private Boolean t2 = false;
               private Boolean t3 = false;
               final int size = 512;
               final int myIterator = 20000;
               WritableImage wr = new WritableImage(size, size);//to bylo przed rect automatem
               PixelWriter pw = wr.getPixelWriter();




               /*
               Różnica między initialize a zwykłym kostruktorem jaet taka że wpierw ładuje się konstruktor,
               następnie FXML
                potem rzeczy z initailize
               * */

               public void initialize() {
                   gc = canvas.getGraphicsContext2D();
                   gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                  // gc.beginPath();


                   //

               }

               public void mouseMoves(MouseEvent mouseEvent) {
               }

               public void rysujTrójkat(Complex p1,Complex p2, Complex p3) {


                   CollinearChecker test = new CollinearChecker(p1,p2,p3);

                   System.out.println(p3.getIm());



                   Complex gamepoint=new Complex(PodajPunktWewnatrzTrojkata.znajdz(p1,p2,p3)); //(((p1.getRe()+p2.getRe()+p3.getRe())/2),(p1.getIm()+p2.getIm()+p3.getIm())/2);
                   System.out.println("Przed if");
                   System.out.println(p3.getIm());

                   if (test.checkIfTheyAreCollinear() == true) {//czyli nie są wspólniniowe
                       System.out.println("Przed pętlą");
                       System.out.println(p3.getIm());
                       double s = Math.random();


                       for (int i = 0; i < myIterator; i++) {
                           s = Math.random();
                           Complex p;
                           if (s <= 0.333) {
                               p = p1;

                               // System.out.println(i);




                           } else if (s <= 0.666) {//(1/3)nie działało
                               p=p2;




                               //   System.out.println(i);


                           } else {
                               p=p3;




                               //   System.out.println(i);
                           }
                           gamepoint.add(p);
                           int a , b;

                           gamepoint.setRe((gamepoint.getRe())/2);
                          gamepoint.setIm((gamepoint.getIm())/2);
                          //System.out.println(a,b);

                           a=(int)gamepoint.getRe();
                           b=(int)gamepoint.getIm();
                          // System.out.println("  "+a+"  "+b);
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

               //*********************************************************

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


                           rysujTrójkat(p1,p2,p3);
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