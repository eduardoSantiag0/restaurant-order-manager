# Gerenciamento de Pedidos do Restaurante Fome Zero 🤌🍝⌛
## 📜 Descrição do Projeto
- Este projeto é uma simulação de um sistema de gerenciamento de pedidos de restaurante, onde garçons ``Garcom`` processam os pedidos dos clientes. A aplicação inclui funcionalidades como geração de pedidos, processamento e gerenciamento de mesas. Utiliza programação concorrente para lidar com múltiplas threads de forma eficiente, simulando operações reais de um restaurante.

## 🧵 Processamento Concorrente
- **Garçons** ``Garcom`` e o **Gerador de Pedidos** ``OrderGenerator`` são implementados como **threads**, permitindo que múltiplas operações ocorram simultaneamente. Isso simula a dinâmica de um restaurante onde vários garçons processam pedidos e um gerador cria novos pedidos de forma contínua.

## 🔧 Funcionalidades 
- **Geração de Pedidos**: Simula a criação de pedidos e atribui-os às mesas.
- **Processamento de Pedidos**: Garçons processam pedidos a partir de uma fila e atualizam o status das mesas.
- **Processamento Concorrente**: Utiliza múltiplas threads para simular operações simultâneas em um ambiente de restaurante.
