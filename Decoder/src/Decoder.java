

 public class Decoder {
     public String data = "";
     public String repeat = "";
     public String finallyOutput = "";
     public boolean isDataSection = true;
     public void input(byte value) {
         if (value <= 9 && value >= 0) {
             if (isDataSection) {
                 if (value == 0) {
                     isDataSection = false;
                 } else {
                     String valueToString = String.valueOf(value);
                     data += valueToString;
                 }
             } else {
                 String valueToString = String.valueOf(value);
                 repeat += valueToString;
                 if(repeat.length() == 4) {
                     finallyOutput = output();
                 }
             }
         }
     }

        public String output() {
         String repeatedString = "";
            if (!repeat.equals("")) {
                int value = Integer.parseInt(repeat);
                repeatedString = data.repeat(value);
            }
            repeat = "";
            data = "";
            isDataSection = true;
            return finallyOutput += repeatedString;
        }

        public void reset() {
         data = "";
         repeat = "";
         finallyOutput = "";
         isDataSection = true;
        }
    }