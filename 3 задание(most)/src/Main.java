// Абстрактный класс для графической библиотеки
abstract class GraphicsLibrary {
    public abstract void init();
    public abstract void drawLine(int x1, int y1, int x2, int y2);
    public abstract void drawCircle(int x, int y, int radius);
}

// Конкретная реализация для DirectX
class DirectXLibrary extends GraphicsLibrary {
    @Override
    public void init() {
        System.out.println("Инициализация DirectX");
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        System.out.println("DirectX: Рисование линии (" + x1 + "," + y1 + ") - (" + x2 + "," + y2 + ")");
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        System.out.println("DirectX: Рисование круга с центром (" + x + "," + y + "), радиусом " + radius);
    }
}

// Конкретная реализация для OpenGL
class OpenGLLibrary extends GraphicsLibrary {
    @Override
    public void init() {
        System.out.println("Инициализация OpenGL");
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        System.out.println("OpenGL: Рисование линии (" + x1 + "," + y1 + ") - (" + x2 + "," + y2 + ")");
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        System.out.println("OpenGL: Рисование круга с центром (" + x + "," + y + "), радиусом " + radius);
    }
}

// Конкретная реализация для Metal
class MetalLibrary extends GraphicsLibrary {
    @Override
    public void init() {
        System.out.println("Инициализация Metal");
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        System.out.println("Metal: Рисование линии (" + x1 + "," + y1 + ") - (" + x2 + "," + y2 + ")");
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Metal: Рисование круга с центром (" + x + "," + y + "), радиусом " + radius);
    }
}

// Абстрактный класс для формы
abstract class Shape {
    protected GraphicsLibrary library;

    protected Shape(GraphicsLibrary library) {
        this.library = library;
    }

    public abstract void draw();
}

// Конкретная реализация для прямоугольника
class Rectangle extends Shape {
    private int x, y, width, height;

    public Rectangle(GraphicsLibrary library, int x, int y, int width, int height) {
        super(library);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        library.drawLine(x, y, x + width, y);          // верхняя сторона
        library.drawLine(x + width, y, x + width, y + height); // правая сторона
        library.drawLine(x + width, y + height, x, y + height); // нижняя сторона
        library.drawLine(x, y + height, x, y);          // левая сторона
    }
}

// Конкретная реализация для круга
class Circle extends Shape {
    private int x, y, radius;

    public Circle(GraphicsLibrary library, int x, int y, int radius) {
        super(library);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        library.drawCircle(x, y, radius);
    }
}

// Клиентский код
public class Main {
    public static void main(String[] args) {
        GraphicsLibrary directX = new DirectXLibrary();
        GraphicsLibrary openGL = new OpenGLLibrary();
        GraphicsLibrary metal = new MetalLibrary();

        Shape rectangleDX = new Rectangle(directX, 10, 10, 20, 30);
        Shape circleGL = new Circle(openGL, 50, 50, 15);
        Shape rectangleMT = new Rectangle(metal, 100, 100, 40, 20);

        rectangleDX.draw();
        circleGL.draw();
        rectangleMT.draw();
    }
}

