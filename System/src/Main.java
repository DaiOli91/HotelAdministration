import Model.*;


public class Main {
    public static void main(String[] args) {
        User user = new Passenger("147859696", "Andrea", "Carrizo", 60, Gender.FEMALE, "Gaboto 5646", "4585858", "something@asomething.com", "pass123", "Mar del Plata");

        System.out.println(user.toString());

        User receptionist = new Receptionist("18956565", "Sofia", "Caceres", 58, Gender.FEMALE, "Bahia Blanca 123", "2235686968", "soficaceres@gmail.com", "sofi1966", Shift.MORNING);

        System.out.println(receptionist.toString());

        User manager = new Manager("13525252", "Calos", "Patriarcado", 61, Gender.MALE, "Patagones 6000", "2235505065", "carlos.patriarcado@gmail.com", "patriarcado123");

        System.out.println(manager.toString());
    }
}
