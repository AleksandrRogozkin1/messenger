package github.com.rogozkin.myed;


public class Message {
    private String name;
    private String status;
    private String ip_adress;
    private String time;

    public Message(String name, String status, String ip_adress, String time) {
        this.name = name;
        this.status = status;
        this.ip_adress = ip_adress;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return status;
    }

    public String getIpAdress() {
        return ip_adress;
    }

    public String getDate() {
        return time;
    }
}
