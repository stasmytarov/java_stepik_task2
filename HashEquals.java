
//Дан класс ComplexNumber. Переопределите в нем методы equals() и hashCode().
//Equals() должен сравнивать экземпляры ComplexNumber по содержимому полей re и im. 
//HashCode() должен быть согласованным с реализацией equals().
//Реализация hashCode(), возвращающая константу или не учитывающая дробную часть re и im, засчитана не будет

final class ComplexNumber { 

  private final double re;
  private final double im;  

  public ComplexNumber(double re, double im) {
      this.re = re;
      this.im = im;
  }

  public double getRe() {
      return re;
  }

  public double getIm() {
    return im;
  }
    

  @Override
  public boolean equals(Object o) { 
    if (o == this) { 
      return true;
    }    
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }    
    ComplexNumber c = (ComplexNumber) o;
      return (re == c.re)
       & (im == c.im);
  } 


  @Override
	public int hashCode() { 
    int result = 18;
    long value1 = Double.doubleToLongBits(re);
    long value2 = Double.doubleToLongBits(im);
    result = 37 * result + (int)(value1 - (value1 >>> 32));
    result = 37 * result + (int)(value2 - (value2 >>> 32));
    return result;   
  }   
}

class HashEquals { 
  public static void main (String args[] ) { 
    ComplexNumber a = new ComplexNumber(2, 2); 
    ComplexNumber b = new ComplexNumber(2, 2);
    if (a.equals(b) == true) {
      System.out.println("Объекты равны");
    }   
      else {
      System.out.println("Объекты не равны");   
      }
    if (a.hashCode() == b.hashCode()) {
      System.out.println("Хэшкоды объектов равны"); 
    }  
      else {
      System.out.println("Хэшкоды объектов не равны");  
      }
  }  
}  
      

  



