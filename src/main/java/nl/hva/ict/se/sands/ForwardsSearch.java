package nl.hva.ict.se.sands;

public class ForwardsSearch {

    private int compareCount = 0;

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     *
     * @param needle   the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    int findLocation(String needle, String haystack) {
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        int searchStart = haystackLength - needleLength;

        // for each char in haystack (from end)
        //   check if first char of needle is equal
        //   if equal:
        //     move one char in haystack and needle
        //   if not equal:
        //     searchStart - needleLength

        for (int i = 0; i >= searchStart; i++) {
            int count = i;
            for (int j = 0; j <= needleLength; j++) {
                if (j == needleLength) {
                    System.out.printf("found \'%s\' in \'%s\' at index %d\n", needle, haystack, i);
                    return i;
                }
                compareCount++;
                boolean check = needle.charAt(j) == haystack.charAt(count);

//                System.out.println("sigjsidg");
                if (check) {
                    count++;
                } else {
//                    String needleRight = needle.substring(j);
//                    int indexOfHaystackCharInNeedle = needleRight.indexOf(haystack.charAt(count));
//                    char preSwitchChar = haystack.charAt(i);
//                    if (indexOfHaystackCharInNeedle != -1) {
//                        i = i + indexOfHaystackCharInNeedle + 1;
//                        System.out.print("line out shift by " + indexOfHaystackCharInNeedle);
//                    } else {
//                        i = i + needleRight.length();
//                        System.out.print("shifted by " + needleRight.length());
//                    }
//                    System.out.println(" - " + preSwitchChar);
                    break;
                }
            }
        }

        return -1;
    }

    /**
     * Returns the number of character compared during the last search.
     *
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch() {
        return compareCount;
    }

    public static void main(String[] args) {
        ForwardsSearch forwardsSearch = new ForwardsSearch();

        String haystack = "needleinthehaystack";
        String needle =   "needle";
//        String haystack = "abacadabrabracabracadabrabrabracad";
//        String needle =   "rab";

        System.out.println("haystack = " + haystack);
        System.out.println("needle   = " + needle);

        int location = forwardsSearch.findLocation(needle, haystack);
        System.out.println("location = " + location);
//        if (location == -1) {
//            System.exit(-1);
//        }
//
//        System.out.println(location);
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < location; i++) {
//            sb.append(" ");
//        }
//        System.out.println(haystack);
//        System.out.println(sb.toString() + needle);

    }

}
