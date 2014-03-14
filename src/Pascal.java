public class Pascal {
    int n = 0;

    public Pascal(int o) {
        n = o;
    }

    int[][] array;

    public void createArray() {
        for (int i = 0; i < n; i++) {
            array = new int[i][i + 1];
        } // Creates a 2D array
    }

    public void createTriangle() {
        for (int i = 0; i < n; i++) {
            array[0][0] = 1;   // First number is 1
            array[i][i] = 1;  // Last number is 1
            if (i > 2) {  // Compensates for error of trying to access a block that doesnt exist
                for (int j = 1; j < i - 1; j++) {  // Calculate all variables in the given array except at position 0 & last
                    array[i][j] = array[i - 1][j] + array[i - 1][j - 1];
                }  // Calculates the next array by querying the two previous arrays positions just as a human would
            }
        }
    }

    public void showScoreboard() {
        for (int i = 0; i < n; i++) {
            for (int p = 0; p < n; p++) {
                System.out.println(array[i][p]);
            }  // Prints all the arrays at all the points
        }
    }

    public static void main(String[] a) {
        Pascal cc = new Pascal(1);
        cc.createArray();
        cc.createTriangle();
        cc.showScoreboard();
    }
}