import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4};
        System.out.println(Arrays.toString((new Array()).search(array)));
    }

    public int[] search(int[] array) throws RuntimeException {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                return java.util.Arrays.copyOfRange(array, i + 1, array.length);
            }
        }

        throw new RuntimeException("Цифра 4 не найдена");
    }

    public boolean check(int[] array) {
        boolean foundOne = false;
        boolean foundFour = false;
        boolean foundOther = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                foundOne = true;
            } else if (array[i] == 4) {
                foundFour = true;
            } else {
                foundOther = true;
            }
        }

        return foundOne && foundFour && !foundOther;
    }
}

