import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String []args) throws IOException {
        Image img= new Image("C:\\Users\\sharj\\IdeaProjects\\AI_Genetic\\src\\rsz_imageb.bmp");
        img.showImage();
        Utility u = new Utility();
        int popSize = 100;
        Random rand = new Random();

        Image [] arrOfImg = new Image [popSize];            //population of 100 generated
        Image [] arrOfImg2 = new Image [popSize];

        for (int i = 0; i < popSize; i++){
            arrOfImg[i] = u.generateRandomImage(img);
            u.fitnessFunction(arrOfImg[i], img);           //calculate and store fitness value
            arrOfImg2[i]=arrOfImg[i];
        }
        for (int gen = 0; gen < 100; gen++) {                     //doing this for the next 100 generations
            if(gen%2==0){
                for (int i = 0; i < popSize; i++){
                    u.fitnessFunction(arrOfImg[i], img);           //calculate and store fitness value
                }
                u.selection(arrOfImg, popSize);         //SELECTION
                for (int i = 0; i < popSize; i++)
                    arrOfImg2[i]=arrOfImg[i];
                if (gen%10 == 0)  {
                    arrOfImg[0].showImage();
                    System.out.println(arrOfImg[0].fitnessValue);
                }
                u.crossover(arrOfImg,arrOfImg2, popSize);    //CROSSOVER
                u.mutation(arrOfImg2, popSize);     //MUTATION
            }
            else{
                for (int i = 0; i < popSize; i++){
                    u.fitnessFunction(arrOfImg2[i], img);           //calculate and store fitness value
                }
                u.selection(arrOfImg2, popSize);         //SELECTION
                for (int i = 0; i < popSize; i++)
                    arrOfImg[i]=arrOfImg2[i];
                if (gen%10 == 0)  {
                    arrOfImg2[0].showImage();
                    System.out.println(arrOfImg2[0].fitnessValue);
                }
                u.crossover(arrOfImg2,arrOfImg, popSize);    //CROSSOVER
                u.mutation(arrOfImg, popSize);     //MUTATION
            }
        }
    }
}
