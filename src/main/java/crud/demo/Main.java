package crud.demo;

import java.util.Scanner;

/**
 * @author ${USER}
 */
public class Main {

    private static Person[] persons;
    private static int say = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Massivin ölçüsü: ");
        int n = sc.nextInt();
        persons = new Person[n];

        while (true) {
            System.out.println("Menu" +
                               "\n\t1 - Create" +
                               "\n\t2 - Read All" +
                               "\n\t3 - Read By Id" +
                               "\n\t4 - Update By Id" +
                               "\n\t5 - Delete By Id" +
                               "\n\t0 - Exit");
            int menuItem = sc.nextInt();
            switch (menuItem) {
                case 1:
                    createPerson(sc);
                    break;
                case 2:
                    readPersons();
                    break;
                case 3:
                    findPersonById(sc);
                    break;
                case 4:
                    updatePerson(sc);
                    break;
                case 5:
                    deletePerson(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Menu Item should be in [1 - 5]");
            }
        }

    }

    private static void findPersonById(Scanner sc) {
        //id-ni scannerden oxu
        System.out.print("Axtardigimiz id: ");
        long id = sc.nextLong();
        //id-esasinda personu axtar
        Person person = getPersonById(id);

        //eger person null ise
        if (person == null) {
            // mesaj cap ele
            System.out.println("Id-si " + id + " olan Person yoxdur");
        } else {
            //eks halda tapilmis personu cap ele
            System.out.println(person);
        }
    }

    private static void updatePerson(Scanner sc) {
        //id-ni scannerden oxu
        //yeni personun melumatlarini scannerden oxuyuruq
        Person newPerson = new Person(sc.nextLong(), //id
                                      sc.next(), //ad
                                      sc.next(), //soyad
                                      sc.nextByte()); //yash

        //update metodunu cagiririq
        Person updatedPerson = updatePersonById(newPerson.getId(), newPerson);
        //eger person null ise
        if (updatedPerson == null) {
            // mesaj cap ele
            System.out.println("Hec bir person deyisdirilmedi");
        } else {
            //eks halda tapilmis personu cap ele
            System.out.println(updatedPerson);
        }
        System.out.println("----------------------");
        System.out.print("Silinəcək şəxsin id-si: ");
    }

    private static void deletePerson(Scanner sc) {
        long idForDelete = sc.nextLong();
        if (deleteById(idForDelete)) {
            System.out.println("Id-si " + idForDelete + " olan Person silindi");
        } else {
            System.out.println("Id-si " + idForDelete + " olan Person tapılmadı");
        }
    }

    public static void createPerson(Scanner sc) {
        System.out.print("id = ");
        long id = sc.nextLong();

        System.out.print("name = ");
        String name = sc.next();

        System.out.print("surname = ");
        String surname = sc.next();

        System.out.print("age = ");
        byte age = sc.nextByte();

        Person p1 = new Person(id, name, surname, age);
        ensureCapacity();
        persons[say++] = p1;
    }

    private static void ensureCapacity() {
        //eger say persons massivinin uzunlu]una beraberdirse
        //yeni massiv yaradin
        //massivin olcusu say*2 olsun
        //persons massivindeki her bir elementi yeni massive uygun indekse kocurun
        //yeni massivi personsa menimsedin
    }

    public static void readPersons() {
        System.out.println("id\t\tname\t\tsurname\t\tage");
        for (int i = 0; i < say; i++) {
            System.out.println(persons[i]);
        }
//        int i = 0;
//        Person p;
//        while ((p =persons[i++]) != null) {
//            System.out.println(p);
//        }
    }

    public static Person getPersonById(long id) {
        //massivdeki her bir person ucun
        for (int i = 0; i < say; i++) {
            // eger personun id-si verilen id-e beraberdirse
            if (persons[i].getId() == id) {
                // hemin personu qaytar
                return persons[i];
            }
        }
        return null;
    }

    public static Person updatePersonById(long id, Person newPerson) {
        //verilmis id esasinda personu axtar
        Person person = getPersonById(id);
        //eger person tapildisa
        if (person != null) {
            //tapilmis personun melumatlarini newPerson obyektinin melumatlari ile evez edin
            person.setName(newPerson.getName());
            person.setSurname(newPerson.getSurname());
            person.setAge(newPerson.getAge());
            //deyisdirilmis personu qaytarin
            return person;
        } else {
            //eks halda
            //null qaytarin
            return null;
        }
    }

    public static boolean deleteById(long id) {
        //her bir person ucun
        for (int i = 0; i < say; i++) {
            //eger personun id-si verilmis id-e beraberdirse
            if (persons[i].getId() == id) {
                //sonraki her bir personu bir evvelki persona menimset
                for (int j = i; j < say - 1; j++) {
                    persons[j] = persons[j + 1];
                }
                //sonda sonuncu persona null menimset
                persons[--say] = null;
                //true qaytar
                return true;
            }
        }
        //eger hec ne tapilmadisa false qaytar
        return false;
    }
}
