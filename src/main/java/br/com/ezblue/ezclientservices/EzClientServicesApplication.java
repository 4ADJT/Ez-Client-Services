package br.com.ezblue.ezclientservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EzClientServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzClientServicesApplication.class, args);
    }

    //TODO: ++Ajuste necessário nas Keys do veiculo, transformar a placa do carro como primaryKey,
    // e alterar as consultas para placa do carro e não pelo o ID do veiculo

    //TODO: +Gerenciamento de Veículos: CRUD de veículos Relacionar veículos a clientes (n>n).
    //TODO: +Histórico de Transações: Registrar e consultar histórico de transações de estacionamento.
    //TODO: +Notificações e Alertas: Enviar notificações aos clientes sobre expiração de tempo de estacionamento,
    // pagamentos, etc.(pode ser futuramente transferido para um serviço dedicado).


    //TODO: -Integração com Serviços de Pagamento: Interface para integrar com serviços de pagamento externos para
    // recarga de contas e pagamento de tarifas. (pode ser futuramente transferido para um serviço dedicado).
    //TODO: -Autenticação e Autorização: Gerenciamento de autenticação e autorização (pode ser futuramente
    // transferido para um serviço dedicado).
    //TODO: -Relatórios e Estatísticas: Geração de relatórios e estatísticas de uso para os clientes. (pode ser
    // futuramente transferido para um serviço dedicado).

}
