package gfviegas;

import java.awt.*;
import java.util.Random;
import robocode.*;
import robocode.util.Utils;

import static java.lang.Double.POSITIVE_INFINITY;

/**
 * Simples enum pra descrever se o robô anda pra frente ou pra trás
 */
enum Direcoes {
    FRENTE(1),
    TRAS(-1);

    private int valor;

    Direcoes(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
};

/**
 * RoboPoc: destruir e lacrar!
 *
 * Persegue um inimigo dando muitos tiros fracos no caminho. Quando está próximo, dá tiros poderosos.
 *
 * @author Gustavo Viegas
 */
public class RoboPoc extends AdvancedRobot {
    private Direcoes direcao = Direcoes.FRENTE;
    private Random random = new Random();
    private boolean estaPerpendicular = false;

    /**
     * Inicialização do Robô
     */
    public void run() {
        // Estilizando o robô
        Color pink = new Color(255, 102, 204);

        setBodyColor(Color.white);
        setGunColor(pink);
        setRadarColor(Color.magenta);
        setScanColor(pink);
        setBulletColor(Color.magenta);

        // Deixa o radar e arma moverem independentes do corpo do tanque
        setAdjustRadarForRobotTurn(true);
        setAdjustGunForRobotTurn(true);

        // O radar fica girando infinitamente pra direita buscando adversários
        turnRadarRightRadians(POSITIVE_INFINITY);
    }

    /**
     * Ao escanear um robô, persegue ele até ficar próximo, metendo bala fraca no caminho. Ao chegar perto, força total!
     * @param e Evento de Robô Escaneado
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Valor absoluto do inimigo. Trava o radar nele.
        double giroAbsoluto = e.getBearingRadians() + getHeadingRadians();

        // Giro relativo a arma
        double giroRelativo = giroAbsoluto - getGunHeadingRadians();

        // Velocidade do inimigo
        double velocidadeLateral = e.getVelocity() * Math.sin(e.getHeadingRadians() - giroAbsoluto);

        // Quanto temos que girar a arma
        double giroArma;

        // Trava o radar (gira o radar pra direita, ao detectar gira tudo que falta pra esquerda, deixando o radar "aberto")
        setTurnRadarLeftRadians(getRadarTurnRemainingRadians());

        // Se o inimigo estiver mais que 150 pixels de distancia...
        if (e.getDistance() > 150) {
            // Atira um pouco mais "aberto" do que quando está próximo
            giroArma = Utils.normalRelativeAngle(giroRelativo + velocidadeLateral / 25);
            setTurnGunRightRadians(giroArma);

            // Prevê onde o inimigo vai, e corre em sua direção
            double velocidadeRelativa = velocidadeLateral / getVelocity();
            setTurnRightRadians(Utils.normalRelativeAngle(giroAbsoluto - getHeadingRadians() + velocidadeRelativa));
            estaPerpendicular = false;
            setAhead((e.getDistance() - 140) * direcao.getValor());

            // Mete bala... intensidade menor já que está longe... Quanto mais longe, mais fraco
            setFire(Math.min(3, 400 / e.getDistance()));
        }  else {
            double velocidadeRelativa = velocidadeLateral / 15;
            giroArma = Utils.normalRelativeAngle(giroRelativo + velocidadeRelativa);
            setTurnGunRightRadians(giroArma);

            // Gira perpendicularmente ao inimigo
            setTurnRight(giroPerpendicular(e.getBearing()));
            estaPerpendicular = true;

            // Anda só a distancia necessaria pra ficar a 140px de distancia, que é a distancia "segura" que definimos.
            setAhead((e.getDistance() - 140) * direcao.getValor());
            setFire(Math.max(3, Math.random () * 4)); // METE BALA!
        }
    }

    /**
     * Ao bater na parede, anda na direção oposta.
     * Não precisamos fazer muita coisa ao atingir paredes porque é improvavél de acontecer, já que perseguimos adversários
     * @param e Evento de atingir uma parede
     */
    public void onHitWall(HitWallEvent e){
        inverteDirecao();
    }

    /**
     * Ao ser atingido por uma tiro, gira perpendicularmente e tenta escapar da possivel linha de fogo
     * @param e Evento de Atingido por um Tiro
     */
    public void onHitByBullet(HitByBulletEvent e) {
        if (!estaPerpendicular) {
            turnRight(giroPerpendicular(e.getBearing()));
            estaPerpendicular = true;
        }

        ahead(e.getPower() * 9.5 * direcao.getValor());
    }

    /**
     * Dá uma dancinha quando ganha :D
     * @param e Evento de Vitória
     */
    public void onWin(WinEvent e) {
        for (int i = 0; i < 10; i++) {
            ahead(5 * i * direcao.getValor());
            inverteDirecao();
        }
    }

    /**
     * Inverte a direção... indo pra frente se estava indo pra trás, e vice-versa.
     */
    private void inverteDirecao() {
        direcao = (direcao.equals(Direcoes.FRENTE)) ? Direcoes.TRAS : Direcoes.FRENTE;
    }


    /**
     * A partir de um angulo, gira o robô pra ficar de lado à ele
     * @param angulo Angulo de referencia
     * @return Valor do angulo relativo pra girar o tanque pra esquerda pra ficar de lado ao angulo de referencia.
     */
    private double giroPerpendicular(double angulo) {
        return 90 + angulo;
    }
}