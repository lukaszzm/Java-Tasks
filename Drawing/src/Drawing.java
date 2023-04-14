public class Drawing implements SimpleDrawing{
    public int[][] canvas;
    public int size;
    public int firstCoordinate;

    public int secondCoordinate;

    @Override
    public void setCanvasGeometry(Geometry input) {
        int getSize = input.getSize();
        if ( getSize > 0) {
            size = getSize;
            canvas = new int[size][size];
        }
        firstCoordinate = input.getInitialFirstCoordinate();
        secondCoordinate = input.getInitialSecondCoordinate();
    }

    @Override
    public void draw(Segment segment) {
        int color = segment.getColor();
        int direction = segment.getDirection();
        int length = segment.getLength();
        if (size > 0 && (direction == -1 || direction == 1 || direction == -2 || direction == 2)) {
            while (true) {
                canvas[firstCoordinate][secondCoordinate] = color;
                length--;
                if (length == 0) {
                    break;
                }
                switch (direction) {
                    case 1 -> {
                        if (firstCoordinate < size - 1) {
                            firstCoordinate++;
                        }
                    }
                    case 2 -> {
                        if (secondCoordinate < size - 1) {
                            secondCoordinate++;
                        }
                    }
                    case -1 -> {
                        if (firstCoordinate > 0) {
                            firstCoordinate--;
                        }
                    }
                    case -2 -> {
                        if (secondCoordinate > 0) {
                            secondCoordinate--;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int[][] getPainting() {
        return canvas;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                canvas[i][j] = 0;
            }
        }
    }
}