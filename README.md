# Лабораторная работа №4 команда "Аппроксимирующий многочлен" (Белицкий Андрей, Кулешов Егор, Дубровин Антон)

## Описание архитектуры проекта 

### package *functions* :
+ ```java 
    public interface Function {

      double apply(final Vector values);

      Matrix hessian(final Vector values);

      Vector grad(final Vector values);
    }
    ```
+ ```java
  class FirstRandom implements Function - квадратичная функция (2x^2+2y^2-8y)
+ ```java
  class TestFunction implements Function - квадратичная функция (x^2+y^2-1.2xy)
+ ```java
  class NonQuadratic implements Function - неквадратичная функция (x^4+2x^2y-33x^2+2xy^2-20x+y^4-19y^2-34y+389)
+ ```java
  class QuasiFirst implements Function (QuasiSecond,QuasiThird,QuasiFourth) - функции для тестирования квазиньютоновских методов

### package *linear*
+ ```java
    class BinarySearch - класс отвечающий за одномерную оптимизацию

### package *objects*
+ ``` record Matrix ``` - рекорд, отвечающий за абстракцию матрицы
    * ```java
      public @NotNull Vector gauss(final Vector b) - метод отвечающий за решение СЛАУ
    * ```java
      public Double get(final int row, final int column) - метод предоставляющий доступ к элементатм матрицы по индексу

    * ```java
      public int size() - метод возвращающий размерность матрицы

    * ```java
      public void set(final int row, final int column, final Double value) - метод позволяющий изменять значения матрицы по индексу

+ ``` record Vector ``` - рекорд, отвечающий за абстракцию вектора
    * ```java
      public Double get(int index) - метод позволяющий получить проекцию вектора на заданную ось
    
    * ```java
      public void set(final int index, final Double value) - метод позволяющий установить значение вектора по переданному индексу
    
    * ```java
      public int size() - метод возвращающий размерность вектора
    
### package *utils*
+ ```java
  class Maths - набор статических методов для операций с матрицами и векторами
+ ```java
  record MethodStats - набор значений, характеризующих работу методов

### package *method*

+ ```java
    public interface Printable {
        default void print(final Vector x) {
            for (int i = 0; i < x.size(); i++) {
                System.out.println(x.get(0) + ", " + x.get(1));
            }
            System.out.println();
        }
    }
+ ```java
    public interface Method extends Printable {
        MethodStats solve(final Vector values, final Function function, final double eps);
    }
+ ```java
    public interface QuasiMethod extends Printable{
        MethodStats solve (final Vector values, final Function function, final double eps, final int methodType);
    }
+ ```java
  abstract class AbstractQuasiMethod implements QuasiMethod - класс предоставляющий общую абстракцию для квазиньютоновских методов
+ ```java
  class Newton implements Method - класс реализующий работу стандартного метода Ньютона
+ ```java
  class NewtonLinearSearch implements Method - класс реалиующий работу метода Ньютона с одномерной оптимиацией
+ ```java
  class NewtonDescent implements Method - класс реализующий работу метода Ньютона с направлением спуска
+ ```java
  class DFP extends AbstractQuasiMethod implements Method - класс реализующий работу метода Давидона-Флетчера-Пауэлла
+ ```java
  class Powell extends AbstractQuasiMethod implements Method - класс реализующий работу метода Пауэлла  
