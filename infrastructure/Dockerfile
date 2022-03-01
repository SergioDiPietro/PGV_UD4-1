FROM ubuntu

RUN apt update && apt install openssh-server sudo -y
RUN useradd -rm -s /bin/bash -g root -G sudo -u 1000 dipi
RUN echo 'dipi:123456' | chpasswd
RUN service ssh start
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]