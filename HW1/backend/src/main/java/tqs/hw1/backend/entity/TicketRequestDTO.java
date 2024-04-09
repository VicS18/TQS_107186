package tqs.hw1.backend.entity;

public class TicketRequestDTO {
    private Long id;
    private String citizenCard;
    private String email;

    public TicketRequestDTO(Long id, String citizenCard, String email) {
        this.id = id;
        this.citizenCard = citizenCard;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCitizenCard() {
        return citizenCard;
    }
    public void setCitizenCard(String citizenCard) {
        this.citizenCard = citizenCard;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
}
